
# TMDB project

Aplicación android con MVVM, Room, Retrofit y Dagger Hilt, coonsumiento la API de TMDB.

To-Do:

☑ Diseño de aplicación con MVVM e inyeccion de depencias con Hilt.

☑ Diseño de tablas/entidades para Room.

☑ Cargar películas populares en carrusel horizontal

☑ Cargar películas por salir en carrusel horizontal

☑ Cargar películas en cines en carrusel horizontal

☑ Ir al detalle de serie/pelicula.

☑ Reproducir videos en detalle de pelicula

☑ La aplicacion funciona parcialmente offline con la persistencia en Room

☑ Pruebas unitarias de casos de uso y view models

[x] Agregar generos de la pelicula en el detalle (añadiendo el campo generos en el modelo de peliculas) y mostrarlos con Chips

[x] separar los modelos de cada tipo de resource y utilizar uno en comun en la capa de dominio
para que siempre quede igual aunque los modelos de base de datos y retrofit cambien (utilizando mappers)

[x] Agregar un bottom navigation para cambiar entre Tv Series y Peliculas utilizando fragments para las vistas.

[x] Agregar un float button en el detalle de la pelicula para poder guardarla en favoritos

[x] Agregar un boton para indicar que  ya la has visto y guardar el estado.

[x] Un nuevo carrusel / vista que permita ver favoritos y/o peliculas ya vistas

