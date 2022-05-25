package ec.sicpa.latam.com.dao;

import java.io.Serializable;
import java.util.Optional;

import ec.sicpa.latam.com.entity.Usuario;
import ec.sicpa.latam.com.exception.ExceptionManager;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author amf
 * @version $1.0$
 */
public interface IUsuarioDao extends IGenericDao<Usuario, Long>, Serializable {

	// @Query("select u from Usuario u where u.username = ?1")
	public Optional<Usuario> findByUsername(String username) throws ExceptionManager;

}
