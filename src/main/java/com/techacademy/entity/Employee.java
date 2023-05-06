package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Length(max=20)
    private String name;

    /** 削除フラグ。null不許可 */
    @Column(nullable = false)
    @NotNull
    private Integer delete_flag;

    /** 登録日時。null不許可 */
    @Column(nullable = false)
    @NotNull
    private java.util.Date created_at;

    /** 更新日時。null不許可 */
    @Column(nullable = false)
    @NotNull
    private java.util.Date updated_at;
}
