function confirmarEliminacion(id,palabraauto) {
    Swal.fire({
        title: 'Confirmar eliminacion',
        text: 'Escribe la palabra '+palabraauto+  ' para confirmar la eliminacion:',
        input: 'text',
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar',
        inputValidator: (value) => {
            if (value === palabraauto) {
                // Redireccionar a la URL de eliminación
                window.location.href = "/Catalogo/EliminarProducto/" + id;
            } else {
                // Mostrar mensaje de error
                return 'La palabra  es incorrecta. Intentalo nuevamente.';
            }
        }
    });
}
