Imports _03_Hello_World_Entidades_Estrandar

Public Class Form1
    Private Async Sub Button1_Click(sender As Object, e As EventArgs) Handles btnSaludar.Click
        Dim persona As New clsPersona
        With persona
            .Nombre = txtNombre.Text
        End With
        MessageBox.Show("Hola " + persona.Nombre)
    End Sub
End Class
