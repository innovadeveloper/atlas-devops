# Documentación del repo bind9

Los archivos alojados en este directorio bind9 son los ùnicos necesarios para realizar la configuraciòn del servidor DNS de forma directa (no inversa) para traducir de dominios a ip's locales. 

# Consideraciones 
- Cambiar el dominio abexa.pe segùn se requiera para las zonas a crearse.
- No se requiere de los archivos de zona indirecta ( ip invertida : 0.168.192 )
- Se requiere instalar el servidor bind9 por ahora como binario dentro del servidor y no tener conflictos por el uso del puerto 53 con docker.
- Modificar la ip del segmento donde vayamos a realizar los filtros de dns (3 octetos : 192.168.0)