package si.development.ahill.beeniusdemo.data.mappers

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 *
 * @param R Response (Remote model)
 * @param E Entity (Local model)
 */
interface DataModelMapper<R, E> {

    fun mapAsEntity(restModel: R): E
}