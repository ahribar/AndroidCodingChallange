package si.development.ahill.beeniusdemo.data.mappers

import si.development.ahill.beeniusdemo.data.models.UserEntity
import si.development.ahill.beeniusdemo.data.rest.models.UserRestModel
import si.development.ahill.beeniusdemo.domain.mappers.DomainModelMapper
import si.development.ahill.beeniusdemo.domain.models.User
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UserMapper @Inject constructor() : DataModelMapper<UserRestModel, UserEntity>,
    DomainModelMapper<UserEntity, User> {

    override fun mapAsEntity(restModel: UserRestModel): UserEntity =
        UserEntity(
            id = restModel.id ?: 0L,
            email = restModel.email,
            name = restModel.name,
            phone = restModel.phone,
            username = restModel.username,
            website = restModel.website,
            city = restModel.address?.city,
            latitude = restModel.address?.geo?.lat,
            longitude = restModel.address?.geo?.lng,
            street = restModel.address?.street,
            suite = restModel.address?.suite,
            zipCode = restModel.address?.zipcode,
            companyBs = restModel.company?.bs,
            companyCatchPhrase = restModel.company?.catchPhrase,
            companyName = restModel.company?.name
        )

    override fun mapAsPresentable(entity: UserEntity): User =
        User(
            id = entity.id,
            username = entity.username,
            name = entity.name
        )
}