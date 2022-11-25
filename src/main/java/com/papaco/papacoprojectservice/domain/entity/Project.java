package com.papaco.papacoprojectservice.domain.entity;

import com.papaco.papacoprojectservice.domain.vo.CodeStore;
import com.papaco.papacoprojectservice.domain.vo.ProjectDescription;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Project {
    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    private Long ownerId;

    @Embedded
    private CodeStore codeStore;

    @Embedded
    private ProjectDescription description;

    public void changeCodeStore(CodeStore codeStore) {
        this.codeStore = codeStore;
    }

    public void editDescription(ProjectDescription description) {
        this.description = description;
    }

    public String getDescription() {
        return description.getDescription();
    }
}
