package com.jutjoy.domain.form.profile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileCreateForm {

    @NotEmpty(message = "*名前は必ず入力してください。")
    @Size(max = 50, message = "*名前は50文字以内で設定してください。")
    private String name;

    @NotEmpty(message = "*性別は必ず選択してください。")
    private String gender;

    @Size(max = 100, message = "*趣味は100文字以内で設定してください。")
    private String hobby;

    @Size(max = 500, message = "*自己紹介は500文字以内で設定してください。")
    private String introduction;
}