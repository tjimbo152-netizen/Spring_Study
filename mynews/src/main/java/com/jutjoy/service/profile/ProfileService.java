package com.jutjoy.service.profile;

import java.util.List; // 追加
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.form.profile.ProfileEditForm; 
import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    // 一覧取得
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    // 新規保存
    public void save(ProfileCreateForm form) {
        Profile profile = new Profile();
        profile.setName(form.getName());
        profile.setGender(form.getGender());
        profile.setHobby(form.getHobby());
        profile.setIntroduction(form.getIntroduction());
        profileRepository.save(profile);
    }
    
    // 編集用：1件取得
    public Profile findById(Integer id) {
        return profileRepository.findById(id).orElse(null);
    }

    // 更新処理
    public void update(ProfileEditForm form) {
        Profile profile = new Profile();
        profile.setId(form.getId());
        profile.setName(form.getName());
        profile.setGender(form.getGender());
        profile.setHobby(form.getHobby());
        profile.setIntroduction(form.getIntroduction());
        profileRepository.save(profile);
    }

    // 削除処理
    public void delete(Integer id) {
        profileRepository.deleteById(id);
    }
}