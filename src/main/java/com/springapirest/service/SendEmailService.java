package com.springapirest.service;

import com.springapirest.model.User;

public class SendEmailService {
	
	private EmailServiceImpl emailService = new EmailServiceImpl();
	
    public void sendWelcomeEmail(User user) {
        String toEmail = user.getUsername();
        String emailSubject = "Bienvenido a A&F Beauty Studio";
        String emailBodyMovil = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "\n" +
                "<head>\n" +
                "\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "\n" +
                "<title>A&F Beauty Studio</title>\n" +
                "\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"margin: 0; padding: 0;\">\n" +
                "\n" +
                "   <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "    <tr>\n" +
                "       <td >\n" +
                "         <table align=\"center\" bgcolor=\"#70bbd9\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">\n" +
                "\n" +
                "            <tr>\n" +
                "              <td align=\"center\" bgcolor=\"#70bbd9\" style=\"color: #ffffff; padding: 10px 0 30px 0; font-family: Arial, sans-serif; font-size: 28px;\">\n" +
                "                 <b>Toolvendor App</b>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td  bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                    <tr>\n" +
                "                       <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n" +
                "                         <b>Bienvenido a Toolvendor App!</b>\n" +
                "                       </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
                "\n" +
                "                      Nunca fue tan facil mejorar los procesos de ventas de su compañia, Le invitamos a gestionar su flota de empleados mediante nuestra plataforma.\n" +
                "\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
                "\n" +
                "                        <h4>A continuación le recordamos sus datos de acceso a su panel de control:</h4>\n" +
                "\n" +
                "        username: "+ user.getUsername() +" \n" +
                "        password: "+ user.getPassword() +"\n" +
                "\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
                "\n" +
                "                      <br><br>Con Toolvendor App puedes administrar tu cartera de clientes y tener acceso directo a tu catalogo de productos.\n" +
                "\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                       <td>\n" +
                "                         <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                          <tr>\n" +
                "                             <td width=\"260\" valign=\"top\">\n" +
                "                               <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                  <tr>\n" +
                "                                    <td align=\"center\" style=\"padding: 30px 0 30px 0;\">\n" +
                "                                       <img src=\"https://s3.amazonaws.com/toolvendor-files-bucket/img/customers.png\" alt=\"\" width=\"100\" height=\"110\" style=\"display: block;\" />\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                  <tr>\n" +
                "                                    <td style=\"padding: 25px 0 0 0;\">\n" +
                "\n" +
                "                                     Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tempus adipiscing felis, sit amet blandit ipsum volutpat sed. Morbi porttitor, eget accumsan dictum, nisi libero ultricies ipsum, in posuere mauris neque at erat.\n" +
                "\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                              </table>\n" +
                "                             </td>\n" +
                "                             <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
                "\n" +
                "                              &nbsp;\n" +
                "\n" +
                "                             </td>\n" +
                "                             <td width=\"260\" valign=\"top\">\n" +
                "                               <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                <tr>\n" +
                "                                  <td  align=\"center\" style=\"padding: 30px 0 30px 0;\">\n" +
                "                                     <img src=\"https://s3.amazonaws.com/toolvendor-files-bucket/img/citas.jpg\" alt=\"\" width=\"100\" height=\"110\" style=\"display: block;\" />\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                  <td style=\"padding: 25px 0 0 0;\">\n" +
                "\n" +
                "                                   Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tempus adipiscing felis, sit amet blandit ipsum volutpat sed. Morbi porttitor, eget accumsan dictum, nisi libero ultricies ipsum, in posuere mauris neque at erat.\n" +
                "\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                                </table>\n" +
                "                             </td>\n" +
                "                          </tr>\n" +
                "                         </table>\n" +
                "                       </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                       <td>\n" +
                "                         <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                          <tr>\n" +
                "                             <td width=\"260\" valign=\"top\">\n" +
                "                               <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                  <tr>\n" +
                "                                    <td align=\"center\" style=\"padding: 30px 0 30px 0;\">\n" +
                "                                       <img src=\"https://s3.amazonaws.com/toolvendor-files-bucket/img/Products.jpg\" alt=\"\" width=\"100\" height=\"110\" style=\"display: block;\" />\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                  <tr>\n" +
                "                                    <td style=\"padding: 25px 0 0 0;\">\n" +
                "\n" +
                "                                     Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tempus adipiscing felis, sit amet blandit ipsum volutpat sed. Morbi porttitor, eget accumsan dictum, nisi libero ultricies ipsum, in posuere mauris neque at erat.\n" +
                "\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                              </table>\n" +
                "                             </td>\n" +
                "                             <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
                "\n" +
                "                              &nbsp;\n" +
                "\n" +
                "                             </td>\n" +
                "                             <td width=\"260\" valign=\"top\">\n" +
                "                               <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                <tr>\n" +
                "                                  <td  align=\"center\" style=\"padding: 30px 0 30px 0;\">\n" +
                "                                     <img src=\"https://s3.amazonaws.com/toolvendor-files-bucket/img/pedidos.jpg\" alt=\"\" width=\"100\" height=\"110\" style=\"display: block;\" />\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                  <td style=\"padding: 25px 0 0 0;\">\n" +
                "\n" +
                "                                   Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tempus adipiscing felis, sit amet blandit ipsum volutpat sed. Morbi porttitor, eget accumsan dictum, nisi libero ultricies ipsum, in posuere mauris neque at erat.\n" +
                "\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                                </table>\n" +
                "                             </td>\n" +
                "                          </tr>\n" +
                "                         </table>\n" +
                "                       </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
                "                        <br>\n" +
                "                        <br>\n" +
                "                        Le deseamos el mayor exito posible ya que su satisfacción es nuestro compromiso.\n" +
                "                        Atentamente, el quipo de soporte Toolvendor App\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                 </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px 30px 30px;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                  <tr>\n" +
                "                    <td  align=\"right\">\n" +
                "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                        <tr>\n" +
                "                          <td>\n" +
                "                          <a href=\"http://www.twitter.com/\">\n" +
                "                            \n" +
                "                          </a>\n" +
                "                          </td>\n" +
                "                           \n" +
                "                        <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>\n" +
                "                          <td>\n" +
                "                          <a href=\"http://www.twitter.com/\">\n" +
                "                           \n" +
                "                          </a>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                    <td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\">\n" +
                "                     &reg; A&F Beauty Studio, 2017<br/>\n" +
                "                     <a href=\"#\" style=\"color: #ffffff;\"><font color=\"#ffffff\">Unsubscribe</font></a> to this newsletter instantly\n" +
                "\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "       </td>\n" +
                "    </tr>\n" +
                "   </table>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    
    	emailService.sendMail(toEmail, emailSubject, emailBodyMovil, "text/html", null);
    }

    public void sendValidationCodeEmail(User user, String verificationCode) {
        String toEmail = user.getUsername();
        String emailSubject = "Validar correo eléctronico";
        String emailBody = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "\n" +
            "<head>\n" +
            "\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
            "\n" +
            "<title>A&F Beauty Studio</title>\n" +
            "\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
            "\n" +
            "</head>\n" +
            "\n" +
            "<body style=\"margin: 0; padding: 0;\">\n" +
            "\n" +
            "   <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "    <tr>\n" +
            "       <td >\n" +
            "         <table align=\"center\" bgcolor=\"#70bbd9\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">\n" +
            "\n" +
            "            <tr>\n" +
            "              <td align=\"center\" bgcolor=\"#70bbd9\" style=\"color: #ffffff; padding: 10px 0 30px 0; font-family: Arial, sans-serif; font-size: 28px;\">\n" +
            "                 <b>A&F Beauty Studio</b>\n" +
            "              </td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "              <td  bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n" +
            "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "                    <tr>\n" +
            "                       <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n" +
            "                         <b>Hola "+user.getName()+",</b>\n" +
            "                       </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
            "                        <br>\n" +
            "                        Usted se ha registrado satisfactoriamente,\n" +
            "                        para completar el registro debe verificar su correo electronico\n" +
            "                        introduzca en la aplicación el siguiente codigo de verificación.<br><br>\n" +
            "\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
            "\n" +
            "                        <h3>Código de verificación: "+verificationCode+"</h3>\n" +
            "\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
            "\n" +
            "                      <br><br>Luego de introducir el código de verificación ya podrá utilizar nuestros servicios.\n" +
            "\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
            "                        <br>\n" +
            "                        <br>\n" +
            "                          Atentamente, el equipo de soporte A&F Beauty Studio\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                 </table>\n" +
            "              </td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "              <td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px 30px 30px;\">\n" +
            "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "                  <tr>\n" +
            "                    <td  align=\"right\">\n" +
            "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
            "                        <tr>\n" +
            "                          <td>\n" +
            "                          <a href=\"http://www.twitter.com/\">\n" +
            "                            \n" +
            "                          </a>\n" +
            "                          </td>\n" +
            "                           \n" +
            "                        <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>\n" +
            "                          <td>\n" +
            "                          <a href=\"http://www.twitter.com/\">\n" +
            "                           \n" +
            "                          </a>\n" +
            "                          </td>\n" +
            "                        </tr>\n" +
            "                        </table>\n" +
            "                    </td>\n" +
            "                    <td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\">\n" +
            "                     &reg; A&F Beauty Studio, 2017<br/>\n" +
            "                     <a href=\"#\" style=\"color: #ffffff;\"><font color=\"#ffffff\">Unsubscribe</font></a> to this newsletter instantly\n" +
            "\n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </table>\n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </table>\n" +
            "       </td>\n" +
            "    </tr>\n" +
            "   </table>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>";
        
        emailService.sendMail(toEmail, emailSubject, emailBody, "text/html", null);
    }

}
