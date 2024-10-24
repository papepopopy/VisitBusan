package com.project.VisitBusan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarm is a Querydsl query type for Alarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarm extends EntityPathBase<Alarm> {

    private static final long serialVersionUID = -1667244464L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarm alarm = new QAlarm("alarm");

    public final QBoardLike boardLike;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final QReply reply;

    public final QReplyLike replyLike;

    public final QSubReply subReply;

    public final QSubReplyLike subReplyLike;

    public final QWarning warning;

    public QAlarm(String variable) {
        this(Alarm.class, forVariable(variable), INITS);
    }

    public QAlarm(Path<? extends Alarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlarm(PathMetadata metadata, PathInits inits) {
        this(Alarm.class, metadata, inits);
    }

    public QAlarm(Class<? extends Alarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardLike = inits.isInitialized("boardLike") ? new QBoardLike(forProperty("boardLike"), inits.get("boardLike")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.reply = inits.isInitialized("reply") ? new QReply(forProperty("reply"), inits.get("reply")) : null;
        this.replyLike = inits.isInitialized("replyLike") ? new QReplyLike(forProperty("replyLike"), inits.get("replyLike")) : null;
        this.subReply = inits.isInitialized("subReply") ? new QSubReply(forProperty("subReply"), inits.get("subReply")) : null;
        this.subReplyLike = inits.isInitialized("subReplyLike") ? new QSubReplyLike(forProperty("subReplyLike"), inits.get("subReplyLike")) : null;
        this.warning = inits.isInitialized("warning") ? new QWarning(forProperty("warning"), inits.get("warning")) : null;
    }

}

