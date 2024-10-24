package com.project.VisitBusan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWarning is a Querydsl query type for Warning
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWarning extends EntityPathBase<Warning> {

    private static final long serialVersionUID = 1846743003L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWarning warning = new QWarning("warning");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath text = createString("text");

    public QWarning(String variable) {
        this(Warning.class, forVariable(variable), INITS);
    }

    public QWarning(Path<? extends Warning> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWarning(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWarning(PathMetadata metadata, PathInits inits) {
        this(Warning.class, metadata, inits);
    }

    public QWarning(Class<? extends Warning> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

