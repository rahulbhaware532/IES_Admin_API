package com.jrtp.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.bindings.DashboardCard;
import com.jrtp.bindings.LoginForm;
import com.jrtp.bindings.UserAccountForm;
import com.jrtp.constants.AppConstants;
import com.jrtp.entity.EligEntity;
import com.jrtp.entity.UserEntity;
import com.jrtp.repository.EligRepo;
import com.jrtp.repository.PlanRepository;
import com.jrtp.repository.UserRepository;
import com.jrtp.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmailUtils emailUtil;

	@Autowired
	private PlanRepository planRepo;

	@Autowired
	private EligRepo EligRepo;

	@Override
	public String login(LoginForm loginForm) {

		UserEntity entity = userRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());
		if (entity == null) {
			return AppConstants.INVALID_CRED;
		}

		if (AppConstants.Y_STR.equals(entity.getActiveSw()) && AppConstants.UNLOCKED.equals(entity.getAccStatus())) {
			return AppConstants.SUCCESS;
		} else {
			return AppConstants.ACCOUNT_LOCKED;
		}
	}

	@Override
	public boolean recoverPwd(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (null == userEntity) {
			return false;
		} else {

			String subject = AppConstants.RECOVER_PWD;
			String body = readEmailBody(AppConstants.PWD_BODY_FILE, userEntity);

			return emailUtil.sendEmail(subject, body, email);
		}
	}

	@Override
	public DashboardCard fetDashboardInfo() {
		long planCount = planRepo.count();

		List<EligEntity> eligList = EligRepo.findAll();

		long approvedCnt = eligList.stream().filter(ed -> ed.getPlanStatus().equals(AppConstants.AP)).count();

		long deniedCnt = eligList.stream().filter(ed -> ed.getPlanStatus().equals(AppConstants.DN)).count();

		double total = eligList.stream().mapToDouble(ed -> ed.getBenefitAmt()).sum();

		DashboardCard card = new DashboardCard();

		card.setPlansCNT(planCount);
		card.setApprovedCnt(approvedCnt);
		card.setDeniedCnt(deniedCnt);
		card.setBenefitAmtGiven(total);

		return card;
	}

	@Override
	public UserAccountForm getUserByEmail(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);

		UserAccountForm user = new UserAccountForm();
		BeanUtils.copyProperties(userEntity, user);

		return user;
	}

	private String readEmailBody(String filename, UserEntity user) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(line -> {
				line = line.replace(AppConstants.FNAME, user.getFullName());
				line = line.replace(AppConstants.PWD, user.getUserPwd());
				line = line.replace(AppConstants.EMAIl, user.getUserEmail());
				sb.append(line).append(System.lineSeparator()); // Ensure line breaks are preserved
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
