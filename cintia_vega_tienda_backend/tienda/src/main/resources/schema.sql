-- Esquema mínimo para la tabla `compra` usada por CompraDAO
CREATE TABLE IF NOT EXISTS compra (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fecha VARCHAR(50) NOT NULL,
  idProveedor INT,
  total DOUBLE,
  INDEX (idProveedor)
);

-- Si deseas FK hacia `proveedor` (ajusta nombres si son distintos):
-- ALTER TABLE compra
--   ADD CONSTRAINT fk_compra_proveedor
--   FOREIGN KEY (idProveedor) REFERENCES proveedor(id)
--   ON DELETE SET NULL ON UPDATE CASCADE;
