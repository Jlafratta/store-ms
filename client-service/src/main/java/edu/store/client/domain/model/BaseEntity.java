package edu.store.client.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@SuperBuilder
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Builder.Default
    @Column(nullable = false)
    protected boolean enabled = true;

    @Builder.Default
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="datetime DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createDate = new Date();

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="datetime DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifyDate = new Date();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(enabled, that.enabled)
                .append(createDate, that.createDate)
                .append(modifyDate, that.modifyDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(enabled)
                .append(createDate)
                .append(modifyDate)
                .toHashCode();
    }
}
