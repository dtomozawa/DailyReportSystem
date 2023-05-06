package com.techacademy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "report")
public class Report {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 日報の日付。null不許可 */
    @Column(nullable = false)
    @NotNull
    private Date report_date;

    /** タイトル。255桁。null不許可 */
    @Column(length = 255, nullable = false)
    @NotNull
    private String title;

    /** 内容。null不許可 */
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    @NotNull
    private String content;

    /** 従業員テーブルのID。外部キー。null不許可 */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull
    private Employee employee;

    /** 登録日時。null不許可 */
    @Column(nullable = false)
    @NotNull
    private Date created_at;

    /** 更新日時。null不許可 */
    @Column(nullable = false)
    @NotNull
    private Date updated_at;
}
