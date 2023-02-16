package com.papaco.papacoprojectservice.project.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TechStack {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    public TechStack(String name) {
        this.name = name;
    }
}
