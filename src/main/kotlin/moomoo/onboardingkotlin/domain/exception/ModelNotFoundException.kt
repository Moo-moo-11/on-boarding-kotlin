package moomoo.onboardingkotlin.domain.exception

data class ModelNotFoundException(
    val model: String,
    val id: Any
) : RuntimeException(
    "주어진 Id($id)에 해당하는 ${model}을 찾을 수 없습니다"
)
