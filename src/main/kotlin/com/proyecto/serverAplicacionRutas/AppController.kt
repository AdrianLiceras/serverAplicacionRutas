package com.proyecto.serverAplicacionRutas

import org.springframework.web.bind.annotation.*

@RestController
class AppController(private val usuarioRepository: UsuarioRepository,private val rutaRepository: RutaRepository) {

    /*@PostMapping("appendUser/{usuario}")
    fun appendUser(@PathVariable usuario: String, @RequestBody contrasenia: String): String {
        val token = crearToken()
        val fecha = Calendar.getInstance()
        println("La contraseña que viene en el requestBody es $contrasenia")
        val posibleUsuario = comprobarUsuario(usuario)

        if (posibleUsuario != null) {
            //si existe el usuario, comprobamos la contra. Si es buena, le renovamos el token
            if (comprobarContraseña(posibleUsuario, contrasenia)) {
                //Si la contra es buena, le doy el token nuevo, lo salvo y devuelvo el token para que siga hacia el juego
                posibleUsuario.token = token
                posibleUsuario.fecha = fecha
                userRepository.save(posibleUsuario)
            } else //Si no, devuelvo:
                return "Contrasenia incorrecta"
        } else {
            val user = User(usuario, contrasenia, token, fecha, generaListaIdsPreguntas())
            userRepository.save(user)
        }
        userRepository.findAll().forEach {
            println(it)
        }
        return token
    }*/
    @GetMapping("getListUbicaciones")
    fun getListaDePreguntas():String{
        var listaRutas=""
        rutaRepository.findAll().forEach {
            listaRutas+=it.toString()
        }

        return listaRutas
    }
    @GetMapping("getUbicaciones")
    fun getUbicaciones(@RequestBody usuario: Usuario):List<Ubicacion>?{

            usuarioRepository.findAll().forEach { usuarioIt->
                if (usuarioIt.nombre==usuario.nombre)
                    usuarioIt.listaRutas.forEach { ruta ->
                        if (ruta.seleccionada)
                           return ruta.listaUbicaciones
                    }
            }
        return null
    }

    @PostMapping("guardar")
    fun guardarProgreso(@RequestBody usuario: Usuario):String{
        usuarioRepository.save(usuario)
        return  "Progreso Guardado"
    }
}