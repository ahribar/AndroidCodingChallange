package si.development.ahill.beeniusdemo.domain.mappers

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 *
 * @param E Entity (Data model)
 * @param D Domain model
 */
interface DomainModelMapper<E, D> {

    fun mapAsPresentable(entity: E): D
}