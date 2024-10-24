package com.project.VisitBusan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubReplyLike is a Querydsl query type for SubReplyLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubReplyLike extends EntityPathBase<SubReplyLike> {

    private static final long serialVersionUID = 1423247970L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubReplyLike subReplyLike = new QSubReplyLike("subReplyLike");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final QSubReply subReply;

    public QSubReplyLike(String variable) {
        this(SubReplyLike.class, forVariable(variable), INITS);
    }

    public QSubReplyLike(Path<? extends SubReplyLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubReplyLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubReplyLike(PathMetadata metadata, PathInits inits) {
        this(SubReplyLike.class, metadata, inits);
    }

    public QSubReplyLike(Class<? extends SubReplyLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.subReply = inits.isInitialized("subReply") ? new QSubReply(forProperty("subReply"), inits.get("subReply")) : null;
    }

}

