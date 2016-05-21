﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GameStore.Domain.Model
{
    public partial class Order
    {
        public Order()
        {
            this.OrderItems = new HashSet<OrderItem>();
        }
        [Required]
        public int OrderId { get; set; }
        [Required]
        public int UserId { get; set; }
        [Required]
        public string Address { get; set; }
        [Required]
        public string CreditCard { get; set; }
        [Required]
        public string ConfirmationNumber { get; set; }
        [Required]
        public DateTime DeliveryDate { get; set; }
        public virtual ICollection<OrderItem> OrderItems { get; set; }
    }
}