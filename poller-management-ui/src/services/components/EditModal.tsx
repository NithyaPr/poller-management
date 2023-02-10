import React, { FC} from 'react';
import Box from '@mui/material/Box';
import { CreateOrUpdateServiceRequest, editService } from '../api/services';
import { useState } from 'react';

import {Avatar, Button, Input, InputLabel} from '@mui/material';
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

interface EditModalProps {
  id: number,
  name?: string,
  url?: string,
  serviceList: () => void 
}

export const EditModal: FC<EditModalProps> =  ({id, name, url, serviceList}) => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [serviceName, setServiceName] = useState(name);
  const [serviceUrl, setServiceUrl] = useState(url);

  const handleServiceNameChange = (event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => setServiceName(event.currentTarget.value)
  const handleServiceUrlChange = (event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => setServiceUrl(event.currentTarget.value)

  const editServices = async ({id,serviceName, serviceUrl}: CreateOrUpdateServiceRequest) => {

    await editService({id, serviceUrl, serviceName}).then(() => {
      console.log("Successfyly edited!")
      serviceList()
      handleClose()
    })
  }

  return (
    <div className="col-sm-12">
      <Button
                    type="submit"
                    id="search-button"
                    className="btn btn-primary"
                    onClick={handleOpen}
                    >
                    EDIT
            </Button>

      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
        <div>
            <div className="col-sm-4">
            <InputLabel children="Name"></InputLabel>
            <Input  name={serviceName}  value= {serviceName}   onChange={handleServiceNameChange} />
            </div>
            <div className="col-sm-4">
            <InputLabel children="Url"></InputLabel>
            <Input  name={serviceUrl}  value= {serviceUrl}   onChange={handleServiceUrlChange} />
            </div>
            <div className="col-sm-12"><Button onClick={() => {
              editServices({id, serviceName,serviceUrl})
              }}>Save</Button></div>
        </div>
            
        </Box>
      </Modal>
    </div>
  );
}