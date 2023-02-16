package com.papaco.papacoprojectservice.project.domain.entity;

import com.papaco.papacoprojectservice.project.domain.vo.CodeStore;
import com.papaco.papacoprojectservice.project.domain.vo.ProjectDescription;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted = false")
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
    private boolean finished = Boolean.FALSE;
    private boolean deleted = Boolean.FALSE;

    public Project(UUID id, Long ownerId, CodeStore codeStore, ProjectDescription description) {
        this.id = id;
        this.ownerId = ownerId;
        this.codeStore = codeStore;
        this.description = description;
    }

    public void changeCodeStore(CodeStore codeStore) {
        this.codeStore = codeStore;
    }

    public void editDescription(ProjectDescription description) {
        this.description = description;
    }

    public String getDescription() {
        return description.getDescription();
    }

    public void finish() {
        this.finished = true;
    }

    public void delete() {
        this.deleted = true;
    }
}
