package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {
    /** 社員番号 */
    @Id
    @Column(name = "code", length = 20, nullable = false, unique = true)
    @NotEmpty
    @Length(max = 20)
    private String code;

    /** パスワード */
    @Column(name = "password", length = 255, nullable = false)
    @NotEmpty
    @Length(max = 255)
    private String password;

    /** 権限 */
    @Column(name = "role", length = 10, nullable = false)
    @NotEmpty
    @Length(max = 10)
    private String role;

    /** 従業員テーブルのID */
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

	
	

	
	}

		
	

