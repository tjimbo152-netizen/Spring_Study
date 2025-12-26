package com.jutjoy.service.profile;

// ... インポート略 ...
import java.util.List;

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

    // 1件取得（編集画面の初期表示用）
    public Profile findById(Integer id) {
        return profileRepository.findById(id).orElse(null);
    }

    // 更新処理
    public void update(ProfileEditForm form) {
    	// 1. まず、データベースから既存のデータを取得する
        Profile profile = profileRepository.findById(form.getId()).orElse(null);
        
        if (profile != null) {
            // 2. 既存のデータに対して、フォームから送られてきた値だけを上書きする
            // これにより、registered_date などの既存の値は保持されます
            profile.setName(form.getName());
            profile.setGender(form.getGender());
            profile.setHobby(form.getHobby());
            profile.setIntroduction(form.getIntroduction());
            profileRepository.save(profile);
        }
    }

    // 削除処理
    public void delete(Integer id) {
        profileRepository.deleteById(id);
    }
}