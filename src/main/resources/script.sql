CREATE DATABASE ferchaskasbd;
USE ferchaskasbd;
SHOW TABLES;
SELECT * FROM sucursales;
SELECT * FROM roles;
SELECT * FROM roles_permisos;
SELECT * FROM usuarios;
SELECT * FROM permisos;

DESCRIBE roles;
DESCRIBE roles_permisos;
DESCRIBE permisos;

-- Operaciones con roles
-- Insertar roles
INSERT INTO roles (
    created_at,
    updated_at,
    descripcion,
    nombre
) VALUES
      (
          NOW(),
          NOW(),
          'Administrador del sistema con acceso total a todas las funciones y configuraciones.',
          'ADMIN'
      ),
      (
          NOW(),
          NOW(),
          'Empleado encargado de realizar ventas y operaciones básicas de caja.',
          'CAJERO'
      ),
      (
          NOW(),
          NOW(),
          'Supervisor encargado de monitorear operaciones, reportes y supervisión del personal.',
          'SUPERVISOR'
      );

-- Ejemplo de relación roles_permisos

-- ADMIN -> permisos 1,2,3,4,5
INSERT INTO roles_permisos (rol_id, permiso_id) VALUES
                                                    (1,1),
                                                    (1,2),
                                                    (1,3),
                                                    (1,4),
                                                    (1,5);

-- CAJERO -> permisos básicos
INSERT INTO roles_permisos (rol_id, permiso_id) VALUES
                                                    (2,1),
                                                    (2,2);

-- SUPERVISOR -> permisos intermedios
INSERT INTO roles_permisos (rol_id, permiso_id) VALUES
                                                    (3,1),
                                                    (3,3),
                                                    (3,4);

-- OPeraciones con permisos
-- Insertar permisos
INSERT INTO permisos (
    created_at,
    updated_at,
    clave,
    descripcion
) VALUES
      (
          NOW(),
          NOW(),
          'ABRIR_CAJA',
          'Permite abrir la caja del sistema'
      ),
      (
          NOW(),
          NOW(),
          'REALIZAR_VENTA',
          'Permite registrar ventas'
      ),
      (
          NOW(),
          NOW(),
          'VER_REPORTES',
          'Permite visualizar reportes del negocio'
      ),
      (
          NOW(),
          NOW(),
          'CANCELAR_VENTA',
          'Permite cancelar ventas realizadas'
      ),
      (
          NOW(),
          NOW(),
          'MODIFICAR_PRECIOS',
          'Permite modificar precios de productos'
      );

