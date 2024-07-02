package com.sparta.storyindays.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimstamped is a Querydsl query type for Timstamped
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QTimstamped extends EntityPathBase<Timstamped> {

    private static final long serialVersionUID = -419854734L;

    public static final QTimstamped timstamped = new QTimstamped("timstamped");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public QTimstamped(String variable) {
        super(Timstamped.class, forVariable(variable));
    }

    public QTimstamped(Path<? extends Timstamped> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimstamped(PathMetadata metadata) {
        super(Timstamped.class, metadata);
    }

}

