package com.JPA_Board.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @Column(name = "created_date")
    private LocalDateTime createdDate;      // 생성일시

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;     // 최종 수정일시

    @PrePersist
    // @PrePersist : insert 전 실행할 메서드 지정
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    // @PreUpdate : update 전 실행 메서드
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }
}
