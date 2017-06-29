# Práctica final Android: Fichas

Escribe una aplicación para compartir fichas de actividades de montaña (escalada, barranquismo, etc...).
La aplicación debe incluir un servidor y un cliente (el servidor actuará únicamente de repositorio y
puedes programarlo en lo que quieras).
El cliente permite crear fichas que incluyan fotos, texto, croquis  (pueden ser fotos también) y localizaciones gps que se visualicen en Google Maps.
Debe permitir también seleccionar fichas en un radio de la posición actual.

Por ejemplo, una ficha puede ser:
Cara sur Naranjo de Bulnes, una descripción, posiciones gps de los puntos de salida, y fotos de los mismos.
Puede haber otra ficha con la cara norte.
Si estoy en Bulnes y pregunto por fichas en un radio adecuado (10Km) me debe mostrar ambas fichas, ver las fotos y el texto, ver los mapas en google maps con las posiciones marcadas, etc.

Las fichas deben guardarse en una base de datos en el teléfono (las partes que se deba, por ejemplo las fotos no) y pueden tener características como dificultad, etc. que permitan filtrarlas.

Adicionalmente, de forma opcional se pueden crear usuarios que puedan compartir su planficiación de una ruta utilizando
la misma aplicación.

# Notas

El servidor usa una biblioteca extra, json-simple.jar.
En el servidor existe una carpeta, la cual tiene los json a utilizar, estos json tienen una ruta definida para las imágenes, por lo que si se cambia la ubicación del servidor, habría que modificar esa ubicación.
En el servidor, la clase json, tiene unos PATH que acceden a los json guardados en la carpeta Climbing.
