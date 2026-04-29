resource "terraform_data" "saludo" {
  triggers_replace = {
    nombre = var.nombre_ambiente
  }

  provisioner "local-exec" {
    command = "echo 'Hola, soy el ambiente ${var.nombre_ambiente} y estoy listo.'"
  }
}