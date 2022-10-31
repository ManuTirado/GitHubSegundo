Imports _03_Hello_World_Entidades_Estrandar

Public Class Form1
    ''' <summary>
    ''' comentarios: Evento asociado al click del bótón saludar. Mostrara por pantalla un saludo más el nombre del textBox
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    Private Async Sub Button1_Click(sender As Object, e As EventArgs) Handles btnSaludar.Click
        If txtNombre.Text.Length <> 0 Then
            Dim persona As clsPersona = New clsPersona(txtNombre.Text)
            MessageBox.Show("Hola " + persona.Nombre)
        End If
    End Sub
End Class
