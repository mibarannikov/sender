package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class DateCreateUpdate {


    @Column(updatable = false)
    private LocalDateTime createdDate;
    @Column
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    @Version
    private Long version;
}
