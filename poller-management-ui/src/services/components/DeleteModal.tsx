import React, { FC, InputHTMLAttributes } from 'react';
import Box from '@mui/material/Box';
import { deleteService } from '../api/services';

import {Avatar, Button, Input, InputLabel} from '@mui/material';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};
 interface DeleteModalProps {
  id: number;
  serviceList: () => void 
}
export const DeleteModal: FC<DeleteModalProps> =  ({id, serviceList}) => {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const deleteServiceFromList = async(id:number) => {
    await deleteService(id.toString()).then((data) => {
      console.log("Deleted successfully ")
      serviceList()
      handleClose()
    }
    )
  }
  return (
    <div>
        <Button onClick={handleOpen}>
      <Avatar alt="edit" src="../../../static/imgs/edit.png" ></Avatar>
      </Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
        <div>
            <div >
            <InputLabel children="Are you sure, Do you want to delete the service " ></InputLabel>
            {id}
            </div>
            <div className="col-sm-3">
            <Button onClick={handleClose}>Cancel</Button>
            </div>
            <div className="col-sm-3"><Button onClick={() => deleteServiceFromList(id)}>Save</Button></div>
        </div>
            
        </Box>
      </Modal>
    </div>
  );
}