package com.example.demo.entity;

import com.example.demo.domain.User;
import com.example.demo.entity.base.BaseInstanceEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user", schema = "demo")
public class UserEntity extends BaseInstanceEntity<User> {

    @Override
    public User convert() {
        return new User(getId());
    }
}