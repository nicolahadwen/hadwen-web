package co.hadwen.web.session;

import co.hadwen.hibernate.HibernateSession;
import co.hadwen.web.session.WebSessionToken.Columns;
import lombok.NonNull;
import org.hibernate.Session;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class WebSessionClient {
    private final Session session;

    public WebSessionClient(@NonNull HibernateSession hibernateSession) {
        this.session = hibernateSession.getSession();
    }

    public Optional<WebSessionToken> get(@NonNull String sessionId) {
        return Optional.ofNullable(session.get(WebSessionToken.class, sessionId));
    }

    public Optional<WebSessionToken> getByUserId(@NonNull String userId) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<WebSessionToken> query = criteriaBuilder.createQuery(WebSessionToken.class);
        Root<WebSessionToken> root = query.from(WebSessionToken.class);
        query.select(root)
                .where(criteriaBuilder.equal(root.get(Columns.USER_ID.getProperName()), userId));
        List<WebSessionToken> results = session.createQuery(query).list();
        return results.size() > 0 ? Optional.of(results.get(0)) : Optional.empty();
    }

    public WebSessionToken create(@NonNull String userId) {
        WebSessionToken sessionToken = new WebSessionToken();
        sessionToken.setUserId(userId);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, WebSessionToken.DAYS_UNTIL_TOKEN_EXPIRY_DEFAULT);
        sessionToken.setExpiryTimestamp(c.getTime());
        session.beginTransaction();
        session.persist(sessionToken);
        session.getTransaction().commit();
        return sessionToken;
    }
}
