package yp.dao;

import yp.model.TFavority;
import yp.model.TUser;

public interface TFavorityDaoI extends BaseDaoI{

	TFavority findByUser(Class<TFavority> class1, TUser user);

}
