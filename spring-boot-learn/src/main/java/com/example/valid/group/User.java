package com.example.valid.group;

import com.example.valid.self.FieldValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.ConvertGroup;
import java.io.Serializable;

/**
 * 分组校验，级联校验，配置验证顺序
 */
@GroupSequence({User.class, PersonForm.class})
public class User implements Serializable {

    @NotNull(message = "用户 id 不能为空", groups = {First.class})
    private Long id;

    /**
     * 使用 el 表达式读取 properties 文件中的信息
     */
    @Length(min = 5, max = 20, message = "{user.name.length}", groups = {First.class, Second.class})
    @Pattern(regexp = "\\w{5,20}", groups = {Second.class})
    private String name;

    /**
     * 自定义校验器
     */
    @NotNull
    @FieldValid
    private String password;

    /**
     * 级联验证 (可能需要转换分组，使用 ConvertGroup 完成)
     */
    @Valid
    @ConvertGroup(from = First.class, to = Second.class)
    private PersonForm form;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", form=" + form +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonForm getForm() {
        return form;
    }

    public void setForm(PersonForm form) {
        this.form = form;
    }
}
