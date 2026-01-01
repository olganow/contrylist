package ru.ya.olganow.contrylist.data;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Entity
@Table(name = "countries")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "iso_code", nullable = false, length = 2, unique = true)
    private String isoCode;

    @Column(name = "last_modify_date", nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifyDate;

    @Column(name = "geometry", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String geometry;

    // Конструкторы
    public CountryEntity() {
        this.lastModifyDate = new Date();
    }

    public CountryEntity(String name, String isoCode, String geometry) {
        this();
        this.name = name;
        this.isoCode = isoCode;
        this.geometry = geometry;
    }

    // Геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public String getGeometry() {
        return geometry;
    }

    // Переопределение JPA
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> objectEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != objectEffectiveClass) {
            return false;
        }
        CountryEntity that = (CountryEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CountryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isoCode='" + isoCode + '\'' +
                ", lastModifyDate=" + lastModifyDate +
                '}';
    }
}