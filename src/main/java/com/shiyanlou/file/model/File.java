package com.shiyanlou.file.model;

import lombok.Data;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 文件实体类
 *
 * @author ma116
 */
@Data
@Table(name = "file")
@Entity
@TableName("file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    @Column(columnDefinition="bigint(20) comment '文件id'")
    private Long fileId;

    @Column(columnDefinition="varchar(500) comment '时间戳名称'")
    private String timeStampName;

    @Column(columnDefinition="varchar(500) comment '文件url'")
    private String fileUrl;

    @Column(columnDefinition="bigint(10) comment '文件大小'")
    private Long fileSize;

    @Column(columnDefinition="int(1) comment '是否是OSS云存储 0-否, 1-是'")
    private Integer isOSS;

    @Column(columnDefinition="int(11) comment '文件引用数量'")
    private Integer pointCount;

    @Column(columnDefinition="varchar(32) comment 'md5标识'")
    private String identifier;

    @Column(columnDefinition="int(1) comment '存储类型 0-本地存储, 1-阿里云存储, 2-FastDFS存储'")
    private Integer storageType;   
}
