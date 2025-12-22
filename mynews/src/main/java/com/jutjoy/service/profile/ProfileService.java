package com.jutjoy.service.profile; // profile を追加

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public void save(ProfileCreateForm form) {
        // フォームからエンティティへ変換
        Profile profile = new Profile();
        profile.setName(form.getName());
        profile.setGender(form.getGender());
        profile.setHobby(form.getHobby());
        profile.setIntroduction(form.getIntroduction());

        // DBに保存
        profileRepository.save(profile);
    }
    
}