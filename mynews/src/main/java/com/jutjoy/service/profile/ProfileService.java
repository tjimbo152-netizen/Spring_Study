package com.jutjoy.service.profile;

// ... インポート略 ...
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.entity.profile.ProfileHistories;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.repository.profile.ProfileHistoriesRepository;
import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileHistoriesRepository profileHistoriesRepository;

    // 一覧取得
    public List<Profile> findAll() {
        return profileRepository.findAllByOrderById();
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
    @Transactional
    public void update(ProfileEditForm form) {
        // 1. データベースから既存のデータを取得（registered_dateを保持するため）
        Profile profile = profileRepository.findById(form.getId()).orElse(null);
        
        if (profile != null) {
            // 2. 既存のデータに対して、フォームから送られてきた値だけを上書きする
            profile.setName(form.getName());
            profile.setGender(form.getGender());
            profile.setHobby(form.getHobby());
            profile.setIntroduction(form.getIntroduction());
            
            // プロフィール本体を保存
            profileRepository.save(profile);

            // ★追加：編集履歴の登録
            // NewsHistoriesを参考に、リレーション用の履歴を保存します
            registerHistory(profile.getId());
        }
    }

    // 編集履歴登録用メソッド（WikiのregisterHistoryを参考に実装）
    private void registerHistory(Integer profileId) {
        ProfileHistories history = new ProfileHistories();
        history.setProfileId(profileId);
        // edited_date は Entity の @LastModifiedDate で自動設定されるため、IDのセットのみでOK
        profileHistoriesRepository.save(history);
    }

    // 削除処理
    public void delete(Integer id) {
        profileRepository.deleteById(id);
    }
}