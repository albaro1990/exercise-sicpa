package ec.sicpa.latam.com.dao.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import ec.sicpa.latam.com.dao.IUsuarioDao;
import ec.sicpa.latam.com.entity.Usuario;
import ec.sicpa.latam.com.exception.ExceptionManager;


public class UsuarioDao extends GenericDao<Usuario, Long> implements IUsuarioDao {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioDao.class);

	public UsuarioDao(JpaEntityInformation<Usuario, Long> ei, EntityManager em) {
		super(ei, em);
	}

	@Override
	public Optional<Usuario> findByUsername(String username) throws ExceptionManager {
		try {
			TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.username =:username", Usuario.class);
			query.setParameter("username", username);
			return Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			LOG.error("findByUsername: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar el registro");
		}
	}
}
