package com.jrtp.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.bindings.UnlockAccForm;
import com.jrtp.bindings.UserAccountForm;
import com.jrtp.entity.UserEntity;
import com.jrtp.repository.UserRepository;
import com.jrtp.utils.EmailUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean createUserAccount(UserAccountForm accForm) {
		UserEntity entity = new UserEntity();

		BeanUtils.copyProperties(accForm, entity);

		// set random psw
		entity.setPwd(generatePsw());

		// set acc status
		entity.setAccStatus("LOCKED");
		entity.setActiveSw("Y");
		userRepository.save(entity);

		String subject = "User Registration";
		String body = readEmailBody("REG_EMAIL_BODY.txt", entity);
		boolean status = emailUtils.sendEmail(subject, body, accForm.getEmail());

		return status;

	}

	@Override
	public List<UserAccountForm> fetchUserAcounts() {
		List<UserEntity> userEntities = userRepository.findAll();

		List<UserAccountForm> users = new ArrayList<>();

		for (UserEntity userEntity : userEntities) {
			UserAccountForm userAccountForm = new UserAccountForm();
			BeanUtils.copyProperties(userEntity, userAccountForm);
			users.add(userAccountForm);
		}
		return users;
	}

	@Override
	public UserAccountForm getUserAccById(Long id) {

		Optional<UserEntity> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			UserEntity userEntity = optional.get();
			UserAccountForm userAccountForm = new UserAccountForm();
			BeanUtils.copyProperties(userEntity, userAccountForm);
			return userAccountForm;
		}

		return null;
	}

	@Transactional
	@Override
	public String changeAccStatus(Long userId, String status) {
		Integer count = userRepository.updateAccStatus(userId, status);
		if (count > 0) {
			return "Status Changed";
		}
		return "Failed To Change";
	}

	@Override
	public String unlockUserAccount(UnlockAccForm unlockAccForm) {

		UserEntity entity = userRepository.findByEmail(unlockAccForm.getEmail());

		entity.setPwd(unlockAccForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");
		userRepository.save(entity);

		return "Account Unlocked";
	}

	private String generatePsw() {
		// Method to generate random text of a given length
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		int length = 6;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}

		return sb.toString();

	}

	private String readEmailBody(String filename, UserEntity user) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(line -> {
				line = line.replace("${FNAME}", user.getFullName());
				line = line.replace("${PWD}", user.getPwd());
				line = line.replace("${EMAIL}", user.getEmail());
				sb.append(line).append(System.lineSeparator()); // Ensure line breaks are preserved
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
