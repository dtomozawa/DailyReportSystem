package com.techacademy.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 氏名。20桁。null不許可 */
    @Column(length = 20, nullable = false)
    @NotNull
    @Length(max = 20)
    private String name;

    /** 削除フラグ。null不許可 */
    @Column(nullable = false)
    @NotNull
    private Integer delete_flag;

    /** 登録日時。null不許可 */
    @Column(nullable = false)
    @NotNull
    private Date created_at;

    /** 更新日時。null不許可 */
    @Column(nullable = false)
    @NotNull
    private Date updated_at;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Authentication authentication;

    /** レコードが削除される前に行なう処理 */
    @PreRemove
    @Transactional
    private void preRemove() {
        // 認証エンティティからemployeeを切り離す // "user"から"employee"に変更
        if (authentication != null) {
            authentication.setEmployee(null); // "setUser"から"setEmployee"に変更
                }
    }}

	