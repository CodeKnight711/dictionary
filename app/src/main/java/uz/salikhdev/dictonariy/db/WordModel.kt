package uz.salikhdev.dictonariy.db

data class WordModel(
    val id: Int,
    val uzb: String,
    val eng: String,
    val isFav: Int
)
