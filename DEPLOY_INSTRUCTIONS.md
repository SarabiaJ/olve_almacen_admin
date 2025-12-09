# Pasos para compilar y desplegar el backend actualizado

## Opción 1: Compilar y ejecutar localmente

Desde la carpeta `/workspaces/olve_almacen_admin/cintia_vega_tienda_backend/tienda`:

```bash
# Compilar el proyecto
./mvnw clean package

# Ejecutar la aplicación
./mvnw spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## Opción 2: Desplegar en Render (si estás usando Render)

1. Haz commit y push de los cambios al repositorio:
```bash
git add .
git commit -m "Fix CORS configuration and PUT mapping paths"
git push origin main
```

2. Render detectará automáticamente los cambios y hará redeploy del backend

3. Espera a que el deploy termine (revisa el panel de Render)

4. Una vez desplegado, prueba los botones en `https://sarabiaj.github.io`

## Cambios realizados en el backend

1. **CorsConfig.java (nuevo)**: Configuración global de CORS que permite:
   - Origen: `https://sarabiaj.github.io` (y otros locales para desarrollo)
   - Métodos: GET, POST, PUT, DELETE, OPTIONS
   - Headers: Todos
   - Credentials: Habilitadas
   - Cache: 3600 segundos

2. **Todos los controladores**: 
   - Actualizado `@PutMapping` para incluir `/{id}` en la ruta
   - Ahora las peticiones PUT a `/api/{entidad}/{id}` funcionarán correctamente

## Si el problema persiste

Si después de desplegar aún ves errores CORS:

1. Verifica que la URL del backend es `https://olve-almacen-admin-backend.onrender.com`
2. Abre DevTools (F12) → Network → busca la petición fallida
3. Verifica los headers:
   - Request debe tener `Origin: https://sarabiaj.github.io`
   - Response debe tener `Access-Control-Allow-Origin: https://sarabiaj.github.io`

Si ves error 404 en PUT: significa que el backend no reconoce la ruta. Verifica que:
- La clase `CorsConfig` esté en el paquete `com.olvealmacen.tienda.config`
- Los controladores tengan `@PutMapping("/{id}")`
- El backend esté compilado con los cambios

## Soporte

Si algo no funciona, revisa:
1. Los logs del servidor (en Render en el panel de logs)
2. La consola del navegador (F12 → Console)
3. La pestaña Network para ver requests/responses
