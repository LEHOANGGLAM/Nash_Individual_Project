import axios from "axios";

export const uploadImage = async (file) => {
    if (!file) throw Error("No file is chosen");

    //console.log(file);

    const formData = new FormData();
    for (let i = 0; i < file.length; i++) {
        if (file[i].size === 0 || file[i].type.split("/")[0] !== "image" || file[i].size > 1048576)
            throw Error("Your file is invalid");

        formData.append("file", file[i]);
        //console.log(file[i]);

    }
    formData.append("upload_preset", "oqlxv53w");
    console.log(formData);


    try {
        let result = await axios.post("https://api.cloudinary.com/v1_1/dmstiyczr/image/upload", formData);
        return {
            // fileName: file.name,
            // publicUrl: result.data.url,
            // createdAt: result.data.created_at,
            // originalFileName: result.data.original_filename,
            // fileFormat: result.data.format,
            // publicID: result.data.public_id,
            result
        };
    } catch (err) {
        return err;
    }
};