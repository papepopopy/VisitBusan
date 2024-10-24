package com.project.VisitBusan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFestivalInfo is a Querydsl query type for FestivalInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestivalInfo extends EntityPathBase<FestivalInfo> {

    private static final long serialVersionUID = 1441269095L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFestivalInfo festivalInfo = new QFestivalInfo("festivalInfo");

    public final QBoard board;

    public final StringPath contactNum = createString("contactNum");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final StringPath homepage = createString("homepage");

    public final StringPath host = createString("host");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath place = createString("place");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath supervision = createString("supervision");

    public QFestivalInfo(String variable) {
        this(FestivalInfo.class, forVariable(variable), INITS);
    }

    public QFestivalInfo(Path<? extends FestivalInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFestivalInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFestivalInfo(PathMetadata metadata, PathInits inits) {
        this(FestivalInfo.class, metadata, inits);
    }

    public QFestivalInfo(Class<? extends FestivalInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board")) : null;
    }

}

