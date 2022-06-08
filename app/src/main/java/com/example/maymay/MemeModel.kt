package com.example.maymay

data class MemeModel(val data: Data) {
    data class Data(val memes: List<Meme>)
    data class Meme(val id: String, val name: String, val url: String)
}