package com.example.cityfootie_compose.datasource.remote

import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.util.DispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class FootieRemoteDataSourceImpl @Inject constructor(
    private val footieAPI: FootieAPI,
    private val dispatcherProvider: DispatcherProvider
) : FootieRemoteDataSource {
    /**
     * Método encargado de obtener el jugador a través del email y de la contraseña.
     *
     * @param email
     * @param password
     * @return Response<Player>
     */
    override suspend fun getPlayer(email: String, password: String): Response<Player> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.getPlayer(email, password)
        }

    /**
     * Método encargado de crear un nuevo usuario.
     *
     * @param newPlayer
     * @return Response<Void>
     */
    override suspend fun postPlayer(newPlayer: Player): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.postPlayer(newPlayer)
        }

    /**
     * Método encargado de modificar los datos del usuario (excepto la contraseña).
     *
     * @param email
     * @param name
     * @param surnames
     * @param username
     * @param number
     * @return Response<Void>
     */
    override suspend fun updatePlayer(
        email: String,
        name: String,
        surnames: String,
        username: String,
        number: Int
    ): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.updatePlayer(email, name, surnames, username, number)
        }

    /**
     * Método encargado de obtener el partido de fútbol creado en cierta pista (obtenida con la latitud y longitud).
     *
     * @param latitude
     * @param longitude
     * @return Response<FootballMatch>
     */
    override suspend fun getFootballMatch(
        latitude: Double,
        longitude: Double
    ): Response<FootballMatch> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.getFootballMatch(latitude, longitude)
        }

    /**
     * Método encargado de crear un partido de futbol nuevo.
     *
     * @param newFootballMatch
     * @return Response<Void>
     */
    override suspend fun postFootballMatch(newFootballMatch: FootballMatch): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.postFootballMatch(newFootballMatch)
        }

    /**
     * Método encargado de apuntar a un usuario al partido ya creado.
     *
     * @param latitude
     * @param longitude
     * @param email
     * @return Response<Void>
     */
    override suspend fun putFootballMatch(
        latitude: Double,
        longitude: Double,
        email: String
    ): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.putFootballMatch(latitude, longitude, email)
        }

    /**
     * Método encargado de obtener un jugador a través del email (evitando la circulación de la contraseña entre pantallas).
     *
     * @param email
     * @return Response<Player>
     */
    override suspend fun getPlayerByEmail(email: String): Response<Player> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.getPlayerByEmail(email)
        }

    /**
     * Método encargado de obtener a los jugadores apuntados a un partido.
     *
     * @param latitude
     * @param longitude
     * @return Response<Set<Player>>
     */
    override suspend fun getPlayersByFootballMatch(
        latitude: Double,
        longitude: Double
    ): Response<Set<Player>> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.getPlayersByFootballMatch(latitude, longitude)
        }

}