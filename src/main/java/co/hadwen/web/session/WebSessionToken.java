package co.hadwen.web.session;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name = "web_session_token", schema="hadwen", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SESSION_TOKEN")
})

public class WebSessionToken implements Serializable {
    public static final int DAYS_UNTIL_TOKEN_EXPIRY_DEFAULT = 1;
    @Getter
    public enum Columns {
        SESSION_TOKEN("SESSION_TOKEN"),
        USER_ID("USER_ID");

        @Getter
        private final String properName;
        Columns(@NonNull String properName) {
            this.properName = properName;
        }
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "co.hadwen.hibernate.IdGenerator"
    )
    @Column(name = "SESSION_TOKEN", nullable = false, length = 36)
    private String sessionToken;

    @Column(name = "USER_ID", nullable = false, length = 36)
    private String userId;

    @Column(name = "EXPIRY_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryTimestamp;

    @Column(name = "CREATED_AT", nullable = false, insertable= false, updatable= false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT", nullable = false, insertable= false, updatable= false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
