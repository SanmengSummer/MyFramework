
package mvp.cn.rx;

/**
 * The root view interface for every mvp view
 *
 * @author Summer
 *
 */
public interface IModel<M extends MvpModel> {

    M CreateModel();
}
