# User

## Fields
| FieldName   | Type                                                                | Requirements                                                              | Description                                                       |
|-------------|---------------------------------------------------------------------|---------------------------------------------------------------------------|-------------------------------------------------------------------|
| firstName   | String                                                              | At least 2 symbols                                                        | User first name                                                   |
| lastName    | String                                                              | At least 2 symbols                                                        | User second name                                                  |
| email       | String                                                              | Must provide true after regex "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" | User email address                                                |
| reviews     | Collection of [OrganizationReview](./OrganizationReview.md)         | -                                                                         | All reviews that this user created                                |
| permissions | Collection of [OrganizationPermission](./OrganizationPermission.md) | -                                                                         | User permissions to organization                                  |
| resource    | [UserResource](./UserResource.md)                                   | -                                                                         | All user related resource like profile image, wallpaper and other |
| role        | Collection of [Role](./Role.md)                                     | -                                                                         | User role                                                         |
| payments    | Collection of [Payment](./Payment.md)                               | -                                                                         | All user payments to organization                                 |
| approves    | Collection of [OrganizationApprove](./OrganizationApprove.md)       | -                                                                         | All organization that approved by this user                       |